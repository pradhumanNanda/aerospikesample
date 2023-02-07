import com.aerospike.client.AerospikeClient;
import java.util.*;
import com.aerospike.client.*;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.policy.WritePolicy;
import com.google.gson.Gson;

import java.util.Set;
import java.util.HashSet;


public class Main {
    static AerospikeClient client;

    //static String HOST = "172.17.0.2";
    static String HOST = "0.0.0.0";
    static Integer PORT = 3000;

    public static final String NS = "test";
    public static final String LIST_SET = "list_set";
    public static void main(String[] args) {

        System.out.println("Started.....");
        AerospikeClient  client = new AerospikeClient(HOST, PORT);

        Set<User> userList = createDummyList();
        System.out.println(userList.size());

        String userData  =  new Gson().toJson(userList);

        Bin bin = new Bin("RELATION", Value.get(userList));

        String pk = "user1";

        Key key = new Key(NS, LIST_SET, pk);
        WritePolicy policy = client.getWritePolicyDefault();
        policy.sendKey = true;

        // client.put(policy, key, bin);
        client.operate(policy, key, ListOperation.set("USERRELATION", 0, Value.get(userData)));

        Record record = client.get(null, key);

        for (String bin1 : record.bins.keySet()) {
            System.out.println(record.getValue(bin1));
        }

        System.out.println("Ended.....");
    }

    private static HashSet<User> createDummyList() {
        HashSet<User> users = new HashSet<>();
        users.add(new User("Prabhu", "123"));
        users.add(new User("Prabhu", "123"));
        users.add(new User("Prabhu", "123"));
        users.add(new User("Prabhu", "123"));
        return users;

    }




}