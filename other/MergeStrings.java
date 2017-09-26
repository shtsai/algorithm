/*
    Given two strings, merge them in a interleaving fashion.
    If one string is longer than the other, append the extra substring at the end.

    For example:
    "abcdef" & "xyz" -> "axbyczdef"

    Cloudera
    09/25/2017
 */

class MergeStrings {
    public String mergeStrings(String a, String b) {
        int i = 0, j = 0;
        int lenA = a.length(), lenB = b.length();
        StringBuilder sb = new StringBuilder();
        while (i < lenA && j < lenB) {
            sb.append(a.charAt(i));
            sb.append(b.charAt(j));
            i++;
            j++;
        }
        if (i < lenA) {
            sb.append(a.substring(i));
        }
        if (j < lenB) {
            sb.append(b.substring(j));
        }
        return sb.toString();
    }
}