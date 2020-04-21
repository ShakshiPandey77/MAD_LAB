public class ActivityB extends PreferenceActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
//setContentView(R.layout.activity_layout_b);
addPreferencesFromResource(R.xml.mypreferences);}
}
