public class MainActivity extends AppCompatActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
}
public void loadActivityB(View v){
Intent i=new Intent(MainActivity.this, ActivityB.class);
startActivity(i);
}
}
