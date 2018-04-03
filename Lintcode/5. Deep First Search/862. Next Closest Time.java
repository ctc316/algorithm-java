public class Solution {

    int time_mins;
    int[] digits;
    String closestTime;
    int closestMins;

    /**
     * @param time: the given time
     * @return: the next closest time
     */
    public String nextClosestTime(String time) {
        // get hh, mm
        String[] timeStr = time.split(":");
        time_mins = Integer.parseInt(timeStr[0]) * 60 + Integer.parseInt(timeStr[1]);

        // get available digits, default with 0 if there is no enough digits
        digits = new int[4];
        for (int i = 0, count = 0; i < time.length(); i++) {
            String d = time.substring(i, i + 1);
            if(!d.equals(":")) {
                digits[count++] = Integer.parseInt(d);
            }
        }

        // Sort
        Arrays.sort(digits);

        // use DFS to find the closest combination
        closestTime = "";
        closestMins = Integer.MAX_VALUE;
        dfs(digits, "");

        return closestTime.substring(0, 2) + ":" + closestTime.substring(2);
    }

    private void dfs(int[] digits, String result) {
        switch (result.length()) {
            // check h
            case 1:
                if (Integer.parseInt(result) > 2) {
                    return;
                }
                break;
            // check hh
            case 2:
                if (Integer.parseInt(result) > 23) {
                    return;
                }
                break;
            // check m
            case 3:
                if (Integer.parseInt(result.substring(2)) > 5) {
                    return;
                }
                break;
            // check mm
            case 4:
                if (Integer.parseInt(result.substring(2)) > 59) {
                    return;
                }
                break;
        }

        // compare
        if (result.length() == 4) {
            int mins = Integer.parseInt(result.substring(0, 2)) * 60 +
                            Integer.parseInt(result.substring(2));

            if (mins <= time_mins) {
                mins += 24 * 60;
            }

            if (mins - time_mins < closestMins) {
                closestMins = mins - time_mins;
                closestTime = result;
            }

            return;
        }

        // put next digit
        for (int i = 0; i < digits.length; i++) {
            if (i < digits.length - 1 && digits[i + 1] == digits[i]) {
                continue;
            }

            result += digits[i];
            dfs(digits, result);
            result = result.substring(0, result.length() - 1);
        }
    }
}