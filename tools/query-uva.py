#!/bin/python

import json
import pickle
import sys
import time
import urllib2

USERID_URL = "http://uhunt.felix-halim.net/api/uname2uid/%s"
PROBLEM_URL = "http://uhunt.felix-halim.net/api/p/num/%s"
USER_SUBMISSIONS_URL = "http://uhunt.felix-halim.net/api/subs/%s"
USER_RANKING_URL = "http://uhunt.felix-halim.net/api/ranklist/%s/0/0"
PROBLEM_BY_ID_URL = "http://uhunt.felix-halim.net/api/p/id/%s"

VERDICTS = { 10 : "Submission error",
             15 : "Can't be judged",
             20 : "In queue",
             30 : "Compile error",
             35 : "Restricted function",
             40 : "Runtime error",
             45 : "Output limit",
             50 : "Time limit",
             60 : "Memory limit",
             70 : "Wrong answer",
             80 : "PresentationE",
             90 : "Accepted" }

cache = None

def get_userid(username):
    global cache

    if not cache['userid'].has_key(username):
        url = USERID_URL % (username,)
        userid = urllib2.urlopen(url).read()
        cache['userid'][username] = userid

    return cache['userid'][username]

def get_user_submissions(username):
    global cache

    if not cache['subs'].has_key(username):
        userid = get_userid(username)
        url = USER_SUBMISSIONS_URL % (userid, )
        cache['subs'][username] = json.loads(json.loads(urllib2.urlopen(url).read())['subs'])
    return cache['subs'][username]

def get_user_problem_submissions(username, probnum):
    subs = get_user_submissions(username)
    return [sub for sub in subs if get_problem_num(sub[1]) == int(probnum)]

def get_user_rankings(username):
    url = USER_RANKING_URL % (get_userid(username,))
    return json.loads(urllib2.urlopen(url).read())

def get_problem_num(probid):
    global cache

    if not cache['problemnum'].has_key(probid):
        url = PROBLEM_BY_ID_URL % (probid, )
        cache['problemnum'][probid] = json.loads(urllib2.urlopen(url).read())['num']
    return cache['problemnum'][probid]

def get_verdict(username, probnum):
    subs = get_user_problem_submissions(username, probnum)
    if len(subs) > 1:
        return VERDICTS[subs[-1][2]]
    return "n/a"

def get_ranking(username):
    return get_user_rankings(username)[0]['rank']

def main(args):
    global cache

    def check_args(args, num):
        if len(args) < num:
            print "Insufficient arguments"
            exit(1)

    try:
        cache = pickle.load(open(".cache", "rb"))
        if time.time() - cache['last_update'] > 60:
            cache['subs'] = {}
    except Exception, e:
        cache = {'userid': {},
                 'subs': {},
                 'problemnum': {},
                 'last_update': time.time()}

    check_args(args, 1)
    if args[0] == 'ranking':
        check_args(args, 2)
        print get_ranking(args[1])
    elif args[0] == 'problem':
        check_args(args, 3)
        print get_verdict(args[1], args[2])
    else:
        print "Unknown command"
        exit(1)

    cache['last_update'] = time.time()
    pickle.dump(cache, open(".cache", "wb"))

if __name__ == "__main__":
    main(sys.argv[1:])
