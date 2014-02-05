#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;
#define MAX(a, b)       (a > b ? a:b)
#define MIN(a, b)       (a < b ? a:b)
#define REP(i, a, b)    for(int i=int(a); i<int(b); i++)
typedef long long       ll;
const int MAX_SIZE = 100001;

struct Material {
	friend ostream& operator<<(ostream& output, const Material& m);
public:
    ll currentHeight, maxHeight, inc;
    int type;

    Material() {}
    Material(ll l, ll u, ll i, int type) : currentHeight(l), maxHeight(u), inc(i), type(type) {}
    Material(const Material& m) : currentHeight(m.currentHeight), maxHeight(m.maxHeight), inc(m.inc), type(m.type) {}
    Material(Material* m) : currentHeight(m->currentHeight), maxHeight(m->maxHeight), inc(m->inc), type(m->type) {}

    bool canReuse()
	{
        return currentHeight + inc <= maxHeight;
    }
    Material* increment()
	{
		//cerr << "increment before: " << currentHeight << " " << inc << endl;
        currentHeight += inc;
		//cerr << "increment after: " << currentHeight << endl;
        return this;
    }

    bool operator()(const Material& m1, const Material& m2) const
	{
		//cerr << "m1: " << m1 << endl;
		//cerr << "m2: " << m2 << endl;
        if(m1.currentHeight == m2.currentHeight)
		{
			//cerr << "difference: " << (m1.type - m2.type) << endl;
			if (m1.type - m2.type < 0)
				return false;
			else
				return true;
//            return m1.type - m2.type;
		}
        if(m1.currentHeight < m2.currentHeight)
		{
			//cerr << "smaller" << endl;
            return false;//-1;
		}
		//cerr << "greater" << endl;
		return true;//1;
    }
};
ostream& operator<<(ostream& output, const Material& m)
{
	output << "("
		<< m.currentHeight << ", "
		<< m.maxHeight 	   << ", "
		<< m.inc 		   << ", "
		<< m.type
		<< ")";
	return output;  // for multiple << operators
}

priority_queue<Material,vector<Material>,Material> the_queue;
vector<int> queries;
vector<Material> materials;
int t, s, q, query;
ll L, U, I, the_max;

int main()
{
    scanf("%d\n", &t);
    REP(cc, 0, t) {
        scanf("%d %d\n", &s, &q);

 		the_queue = priority_queue<Material, vector<Material>, Material>();
		queries.clear();
		materials.clear();

        REP(i, 0, s) {
            scanf("%lld %lld %lld\n", &L, &U, &I);
			Material m = Material(L, U, I, i + 1);
			//cerr << "Case #" << (cc+1) << ": material = " << m << endl;
            the_queue.push(m);
        }

        the_max = 0;
        REP(i, 0, q) {
            scanf("%d\n", &query);
			//cerr << "Case #" << (cc+1) << ": query = " << query << endl;
            queries.push_back(query);
            the_max = MAX(the_max, query);
        }
		//cerr << "Maximum = " << the_max << endl;

		/*
		cerr << "size = " << the_queue.size() << endl;
		while (!the_queue.empty())
		{
			cerr << "top material: " << the_queue.top() << endl;
			the_queue.pop();
		}
		return 0;
		*/

        while(1) {
            if(the_queue.empty())
            {
                break;
            }
            Material top = the_queue.top();
			the_queue.pop();
            materials.push_back(Material(top));
            if((int)materials.size() > (the_max + 1)
			|| (int)materials.size() > MAX_SIZE) {
                break;
            }
            if(top.canReuse())
			{
                the_queue.push(top.increment());
            }
        }

        REP(i, 0, q)
		{
            Material m = materials[queries[i] - 1];
            printf("%lld %d\n", m.currentHeight, m.type);
        }
    }
    return 0;
}
