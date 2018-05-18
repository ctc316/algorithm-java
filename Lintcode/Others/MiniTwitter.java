/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *
 *     }
 * }
 */


public class MiniTwitter {

    private static final int NUM_OF_POSTS = 10;
    private static Comparator<Tweet> idComparator;
    private Map<Integer, ArrayList<Tweet>> usrTweetsMap;
    private Map<Integer, ArrayList<Integer>> usrFollowingMap;

    public MiniTwitter() {
        // do intialization if necessary
        this.usrFollowingMap = new HashMap<>();
        this.usrTweetsMap = new HashMap<>();
        if(this.idComparator == null) {
            this.idComparator = new Comparator<Tweet>() {
                @Override
                public int compare(Tweet t1, Tweet t2){
                    return  t1.id < t2.id ? 1 : -1;
                }
            };
        }
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        // write your code here
        Tweet newTweet = Tweet.create(user_id, tweet_text);
        ArrayList<Tweet> tlist = this.usrTweetsMap.get(user_id);
        if(tlist == null) {
            tlist = new ArrayList<Tweet>();
            tlist.add(newTweet);
            this.usrTweetsMap.put(user_id, tlist);
        } else {
            tlist.add(newTweet);
        }
        return newTweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here
        ArrayList<Integer> flist = this.usrFollowingMap.get(user_id);
        ArrayList<Tweet> feeds = new ArrayList<Tweet>(this.getTimeline(user_id));
        if(flist == null) return feeds;
        for (int i = 0; i < flist.size(); i++) {
			feeds.addAll(this.getTimeline(flist.get(i)));
			Collections.sort(feeds, idComparator);
			for(int x = feeds.size()-1; x >= NUM_OF_POSTS; x--) feeds.remove(x);
		}
        return feeds;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here
        ArrayList<Tweet> tlist = this.usrTweetsMap.get(user_id);
        if(tlist == null) return new ArrayList<Tweet>();
        if(tlist.size() > NUM_OF_POSTS) {
            tlist = new ArrayList<Tweet>(tlist.subList(tlist.size()-NUM_OF_POSTS, tlist.size()));
        } else {
            tlist = tlist = new ArrayList<Tweet>(tlist);
        }
        Collections.reverse(tlist);
        return tlist;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        ArrayList<Integer> flist = this.usrFollowingMap.get(from_user_id);
        if(flist == null) {
            flist = new ArrayList<Integer>();
            flist.add(to_user_id);
            this.usrFollowingMap.put(from_user_id, flist);
        } else {
            flist.add(to_user_id);
        }
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        ArrayList<Integer> flist = this.usrFollowingMap.get(from_user_id);
        if(flist != null) {
            flist.remove(new Integer(to_user_id));
            if(flist.size() == 0) {
                this.usrFollowingMap.remove(from_user_id);
            }
        }
    }
}




// TEST CASE
// postTweet(1, "1 is Good!!!")
// postTweet(1, "2 is Good!!!")
// postTweet(1, "3 is Good!!!")
// postTweet(2, "1 is Good!!!")
// postTweet(2, "2 is Good!!!")
// postTweet(2, "3 is Good!!!")
// postTweet(2, "4 is Good!!!")
// postTweet(2, "5 is Good!!!")
// postTweet(2, "6 is Good!!!")
// postTweet(2, "7 is Good!!!")
// postTweet(2, "8 is Good!!!")
// postTweet(2, "9 is Good!!!")
// postTweet(2, "10 is Good!!!")
// postTweet(2, "11 is Good!!!")
// postTweet(2, "12 is Good!!!")
// postTweet(2, "13 is Good!!!")
// postTweet(3, "1 is Good!!!")
// postTweet(3, "2 is Good!!!")
// postTweet(3, "3 is Good!!!")
// getTimeline(1)
// getTimeline(1)
// getTimeline(2)
// getTimeline(2)
// getNewsFeed(1)
// follow(2, 1)
// follow(2, 3)
// getNewsFeed(2)
// unfollow(2, 1)
// getNewsFeed(2)
