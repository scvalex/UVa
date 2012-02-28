#include <tr1/unordered_map>
#include <stdio.h>	// C version
#include <cstdio>	// C++ version
#include <iostream>
#include <cassert>
#include <algorithm>
#include <string>
#include <cstdlib>
#include <cstring>
#include <queue>
#include <vector>
#include <string.h>
#include <ctime>
#include <stdlib.h>
#include <math.h>
#include <string>
#include <locale>
#include <sys/time.h>
#include <iterator>
using namespace std;
#define REP(i, a, b)    for(int i=int(a); i<int(b); ++i)
const int MAX_WORD_LENGTH = 22;
typedef pair<int, string> PIS;
tr1::unordered_map<string, vector<string> > dict;
vector<string> allWords, allSuffixes, queries;
tr1::unordered_map<string, tr1::unordered_map<int, PIS> > str_count;
int N, M;
string sz_word, sz_suffix;

inline bool wordsEqual(const string& word1, const string& word2)
{
	int word1_len = word1.length(), word2_len = word2.length();
	if (word1_len != word2_len)
	{
		return false;
	}
	for (int i=0; i<word1_len && word1[i] != ' ' && word2[i] != ' '; ++i)
	{
		if (word1[i] != word2[i])
		{
			return false;
		}
	}
	return true;
}

int main()
{
	//clock_t start = clock();
	//ios::sync_with_stdio(false);
	scanf("%d %d\n", &N, &M);
	REP(i,0,N)
	{
		getline(cin, sz_word);
		allWords.push_back(sz_word);
	}
	/*
	for (vector<string>::iterator it = allWords.begin(); it!=allWords.end(); ++it) {
		cout << int(it-allWords.begin()) << ": " << *it << endl;
	}
	*/
	//cerr << ( ( clock() - start ) / (double)CLOCKS_PER_SEC )
	//	 << " seconds" << endl;

	vector<string> dictSuffix;
	tr1::unordered_map<int, PIS> countSuffix;
	REP(i,0,M)
	{
		getline(cin, sz_word);
		queries.push_back(sz_word);
		sz_suffix = sz_word;
		size_t found = sz_word.rfind(' ');
  		if (found != string::npos)
		{
			sz_suffix = sz_word.substr(found+1);
		}
		allSuffixes.push_back(sz_suffix);
		/*
		cout << "-------------" << i << "--------------" << endl;
		cout << "word: '"  << sz_word << "'" << endl;
		cout << "suffix: '" << sz_suffix << "'" << endl;
		*/
		dict[sz_suffix] = dictSuffix;
		str_count[sz_suffix] = countSuffix;
	}
	/*
	for (vector<string>::iterator it = allSuffixes.begin(); it!=allSuffixes.end(); ++it) {
		cout << int(it-allSuffixes.begin()) << ": " << *it << endl;
	}
	*/
	//cerr << ( ( clock() - start ) / (double)CLOCKS_PER_SEC )
	//	 << " seconds" << endl;

	sort(allWords.begin(), allWords.end());

	// loop optimisations
	tr1::unordered_map<string, vector<string> >::const_iterator dict_end(dict.end());
	tr1::unordered_map<int, PIS>::iterator it;
	REP(i,0,N)
	{
		sz_word = allWords[i];
		int len = sz_word.length();
		REP(j, 0, len+1)
		{
			sz_suffix = sz_word.substr(j);
			if (dict.find(sz_suffix) != dict_end)
			{
				// dict contains key suffix
				dict[sz_suffix].push_back(sz_word);

				// pre: str_count contains key suffix
				it = str_count[sz_suffix].find(len);
				if (it == str_count[sz_suffix].end())
				{
					str_count[sz_suffix][len] = PIS(1, sz_word);
				}
				else
				{
					(it->second).first++;
				}
			}
		}
	}
	//cerr << ( ( clock() - start ) / (double)CLOCKS_PER_SEC )
	//	 << " seconds" << endl;
	/*
	cout << dict << endl;
	cout << "\tsize of dict = " << dict.size() << endl;
	cout << str_count << endl;
	cout << "\tsize of str_count = " << str_count.size() << endl;
	*/

	// sort for binary search later
	tr1::unordered_map<string, vector<string> >::iterator dict_it;
	for (dict_it = dict.begin(); dict_it!=dict_end; ++dict_it)
	{
		if ((dict_it->second).size() > 1)
		{
			sort((dict_it->second).begin(), (dict_it->second).end());
		}
	}

	REP(i,0,M)
	{
		sz_suffix = allSuffixes[i];
		sz_word = queries[i];
		/*
		cout << "-------------" << i << "--------------" << endl;
		cout << "word: '"  << sz_word << "'" << endl;
		cout << "suffix: '" << sz_suffix << "'" << endl;
		*/

		int word_len = sz_word.length();
		if (dict.find(sz_suffix) == dict_end)
		{
			cout << "not found" << endl;
			continue;
		}

		// suffix is found
		if (word_len > 0 && sz_word[0] == ' ')
		{
			tr1::unordered_map<int, PIS>::iterator it = str_count[sz_suffix].find(word_len);
			if (it == str_count[sz_suffix].end() || (it->second).first == 0)
			{
				cout << "not found" << endl;
			}
			else if ((it->second).first == 1)
			{
				cout << (it->second).second << endl;
			}
			else
			{
				cout << "not unique" << endl;
			}
			continue;
		}
		/*
		cout << "Showing words[it] / word..." << endl;
		for (vector<string>::iterator it = words.begin(); it!=words.end(); ++it) {
			cout << int(it - words.begin())  << ": " << *it << " " << word << endl;
		}
		*/
		// part numbers in catalog are unique
		vector<string>::const_iterator words_end(dict[sz_suffix].end());
	    vector<string>::iterator lb = lower_bound(dict[sz_suffix].begin(),
			dict[sz_suffix].end(), sz_word);
		//int index = distance(dict[sz_suffix].begin(), it);
		//cerr << dict[sz_suffix] << " " << dict[sz_suffix].size() << endl;
		if (lb!=words_end && wordsEqual(sz_word, *lb))
		{
			if ( (lb+1)<words_end && wordsEqual(sz_word, *(lb+1)))
			{
				cout << "not unique" << endl;
			}
			else
			{
				cout << *lb << endl;
			}
		}
		else
		{
			cout << "not found" << endl;
		}
	}
	//cerr << ( ( clock() - start ) / (double)CLOCKS_PER_SEC )
	//	 << " seconds" << endl;
	return 0;
}
