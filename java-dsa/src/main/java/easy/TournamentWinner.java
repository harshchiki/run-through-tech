package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TournamentWinner {
    public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> scoreMap = new HashMap<>();

        for(int i = 0; i < competitions.size(); i++) {
            String first = competitions.get(i).get(0);
            String second = competitions.get(i).get(1);
            if(results.get(i) == 1) {
                // first wins
                if(scoreMap.containsKey(first)) {
                    scoreMap.put(first, scoreMap.get(first) + 3);
                } else {
                    scoreMap.put(first, 3);
                }

                if(!scoreMap.containsKey(second)) {
                    scoreMap.put(second, 0);
                }
            } else if(results.get(i) == 0) {
                // second wins
                if(scoreMap.containsKey(second)) {
                    scoreMap.put(second, scoreMap.get(second) + 3);
                } else {
                    scoreMap.put(second, 3);
                }

                if(!scoreMap.containsKey(first)) {
                    scoreMap.put(first, 0);
                }
            }
        }
        String winner = "";
        int maxScore = 0;

       for(Map.Entry<String, Integer> score : scoreMap.entrySet()) {
           if(score.getValue() > maxScore) {
               winner = score.getKey();
               maxScore = score.getValue();
           }
       }

        return winner;
    }
}
