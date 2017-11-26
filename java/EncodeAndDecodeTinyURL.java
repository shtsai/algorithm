/*
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// Solution 4: Similar to solution 3, but with random number
public class Codec {
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    Random rand = new Random();
    String key = getRand();

    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = getRand();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}


// Solution 3: Use incremental index and HashMap
// Very simple, the the range is constraint within [0: MAX_VALUE]
// 11/16/2017
// version 2: use integers and alphabets
public class Codec {
    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    int count = 1;
    public String getString() {
        int c = count;
        StringBuilder sb = new StringBuilder();
        while (c > 0) {
            c--;
            sb.append(chars.charAt(c % 62));
            c /= 62;
        }
        return sb.toString();
    }
    public String encode(String longUrl) {
        String key = getString();
        map.put(key, longUrl);
        count++;
        return "http://tinyurl.com/" + key;
    }
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}

// version 1: purely integer
public class Codec {
    HashMap<Integer, String> map = new HashMap<>();
    int count = 0;
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String host = "http://tinyurl.com/";
        map.put(count, longUrl);
        return host + String.valueOf(count++);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""));
        return map.get(index);
    }
}

// Solution 2: use String.valueOf
// 
// Add the long url to a list and convert its index to a string.
// Then decode, convert string back to its integer value and 
// use that as index back to the list.
//
// Problems: 1. short urls are incremental (by 1 every time)
//           2. same long url will get multiple different short url (wasteful)

public class Codec {
    ArrayList<String> list = new ArrayList<>();
    String host = "http://tinyurl.com/";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        list.add(longUrl);
        return host + String.valueOf(list.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl.replace(host, ""));
        if (index >= list.size() || index < 0) throw new IllegalArgumentException();
        String original = list.get(index);
        return original;
    }
}
// -----------------------------------------------------------//

// Solution 1: Use hash code
// convert long url to hash code and use that as short url
// need to check for collision as two long urls might have
// the same hash code

public class Codec {
    HashMap<Integer, String> map = new HashMap<>();
    String host = "http://tinyurl.com/";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int hash = longUrl.hashCode();
        while (map.containsKey(hash)) hash++;
        map.put(hash, longUrl);
        return host + hash;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String original = map.get(Integer.parseInt(shortUrl.replace(host, "")));
        return original;
    }
}

