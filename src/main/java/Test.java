import com.highmobility.oemcloudsdk.HMServer;

public class Test {
    void test() {
        HMServer server = new HMServer();

        server.setListener(command -> {

        });

        server.connect("token");
    }
}
