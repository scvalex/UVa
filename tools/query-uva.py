#!/bin/env python

import json
import pickle
import sys
import urllib2

USERID_URL = "http://uhunt.felix-halim.net/api/uname2uid/%s"
PROBLEM_URL = "http://uhunt.felix-halim.net/api/p/num/%s"
USER_SUBMISSION_URL = "http://uhunt.felix-halim.net/api/subs-nums/%s/%s/0"
USER_RANKING_URL = "http://uhunt.felix-halim.net/api/ranklist/%s/0/0"

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

userid_cache = None

def get_userid(username):
    global userid_cache

    if not userid_cache.has_key(username):
        url = USERID_URL % (username,)
        userid = urllib2.urlopen(url).read()
        userid_cache[username] = userid

    return userid_cache[username]

def get_problem(probnum):
    url = PROBLEM_URL % (str(probnum),)
    return json.loads(urllib2.urlopen(url).read())

def get_user_submissions(username, probnum):
    userid = get_userid(username)
    url = USER_SUBMISSION_URL % (userid, str(probnum))
    return json.loads(urllib2.urlopen(url).read())[userid]['subs']

def get_user_rankings(username):
    url = USER_RANKING_URL % (get_userid(username,))
    return json.loads(urllib2.urlopen(url).read())

def get_verdict(username, probnum):
    subs = get_user_submissions(username, probnum)
    if len(subs) > 1:
        return VERDICTS[subs[-1][2]]
    return "n/a"

def get_ranking(username):
    return get_user_rankings(username)[0]['rank']

def main(args):
    global userid_cache

    def check_args(args, num):
        if len(args) < num:
            print "Insufficient arguments"
            exit(1)

    try:
        userid_cache = pickle.load(open(".userid_cache", "rb"))
    except Exception:
        userid_cache = {}

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

    pickle.dump(userid_cache, open(".userid_cache", "w"))

if __name__ == "__main__":
    main(sys.argv[1:])
