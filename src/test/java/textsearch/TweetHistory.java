package textsearch;

import java.util.*;

public class TweetHistory implements TextSearch  {

    ArrayList<String> th = new ArrayList<>();

    public TweetHistory() {
        th = new ArrayList<>();
    }

    public boolean add(String s) {
        if (s.length() <= 280) {
            th.add(s);
            return true;
        } else {
            return false;
        }
    }

    public String getPopularHashtag() {
        String hashtag = null;
        String occurence;
        String addtomap;
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < th.size(); i++) {
            occurence = th.get(i);
            for (int j = 0; j < occurence.length(); j++) {
                if (occurence.charAt(j) == '#') {
                    int k = j;
                    while (occurence.charAt(k) != ' ' && (occurence.charAt(k) != '#' || k == j) && k < occurence.length()) {
                        sb.append(occurence.charAt(k));
                        k++;
                        if (k == occurence.length()) {
                            break;
                        }
                    }
                    addtomap = sb.toString();
                    sb = new StringBuilder();
                    if (hm.containsKey(addtomap)) {
                        hm.put(addtomap, hm.get(addtomap) + 1);
                    } else {
                        hm.put(addtomap, 1);
                    }
                }
            }
        }
        int maxValue = (Collections.max(hm.values()));  //source: https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == maxValue) {
                hashtag = entry.getKey();
            }
        }

        return hashtag;
    }


    @Override
    public int getNumOccurrences(String searchString) {
        int numoccurences = 0;
        String occurence;
        for (int i = 0; i < th.size(); i++) {
            occurence = th.get(i);
            for (int j = 0; j < occurence.length(); j++) {
                if (occurence.charAt(j) == searchString.charAt(0)) {
                    for (int k = 0; k < searchString.length(); k++) {
                        if (occurence.charAt(j + k) != searchString.charAt(k)) {
                            break;
                        }
                        if (k == searchString.length() - 1) {
                            numoccurences ++;
                        }
                    }
                }
            }
        }


        return numoccurences;
    }

    @Override
    public List<String> getOccurrences(String searchString) {
        ArrayList<String> returnedlist = new ArrayList<>();
        for (int i = 0; i < th.size(); i++) {
            for (int j = 0; j < th.get(i).length(); j++)
                if (th.get(i).charAt(j) == searchString.charAt(0)) {
                    for (int k = 0; k < searchString.length(); k++) {
                        if (th.get(i).charAt(j + k) != searchString.charAt(k)) {
                            break;
                        }
                        if (k == searchString.length() - 1) {
                            if (!returnedlist.contains(th.get(i))) {
                                returnedlist.add(th.get(i));
                            }
                        }

                    }
                }
        }
        Collections.sort(returnedlist);
        return returnedlist;
    }


}
