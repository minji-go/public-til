```java
String a1 = "a";
        String a2 =  new String(a);
        String a3 =  new String("a");


        Name name1 = new Name("a");
        Name name2 = new Name("a");
        Name name3 = new Name(new String("a"));


        HashSet<String> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        HashSet<String> set1 = new HashSet<>(set);
        set.remove(c);
        HashSet<String> set2 = new HashSet<>(set);
        set.add(c);
        HashSet<String> set3 = new HashSet<>(set);


        ArrayList<String> list1 = new ArrayList<>();
        list1.add(a);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add(a);
        ArrayList<String> list3 = new ArrayList<>();
        list3.add(new String("a"));


        HashMap<String, String> map1 = new HashMap<>();
        map1.put(a, "1");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("a", "1");
        HashMap<String, String> map3 = new HashMap<>();
        map3.put(a, new String("1"));
        HashMap<String, String> map4 = new HashMap<>();
        map4.put(b, "1");
```