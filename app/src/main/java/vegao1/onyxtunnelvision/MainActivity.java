package vegao1.onyxtunnelvision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TunnelView view = new TunnelView(this);
        setContentView(view);
        System.out.println("Completed onCreate");
    }
}