package `in`.co.shuklarahul.soquestionfirestore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)
        findViewById<Button>(R.id.readFromDB).setOnClickListener(View.OnClickListener {
            readFromDB();
        })

        findViewById<Button>(R.id.launchDetail).setOnClickListener(View.OnClickListener {
            launchDetailActivity();
        })

        findViewById<View>(R.id.launchDetailDD).setOnClickListener(View.OnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        })
    }

    private fun launchDetailActivity() {
        val instance = FirebaseFirestore.getInstance()
        Log.e("Instance -> ", "" + instance)
        instance.collection("documents")
            .document("5JwvxO9yWE6UONlh5bU3")
            .get()
            .addOnCompleteListener {
                Log.e("TaskComplete", "Task isSuccessful = ${it.isSuccessful}")
                if(it.isSuccessful) {
                    Log.e("TaskComplete", "Is Result from cache = ${it.result!!.metadata.isFromCache}")
                }else {
                    Log.e("TaskComplete", "Task Failed")
                }
            }
    }

    private fun readFromDB() {
        val instance = FirebaseFirestore.getInstance()
        Log.e("Instance -> ", "" + instance)
        instance.collection("documents")
            .document("5JwvxO9yWE6UONlh5bU3")
            .get(Source.CACHE)
            .addOnSuccessListener {
                Log.e("TaskComplete", "Task isSuccessful = ${it.metadata.isFromCache}")
            }
            .addOnCompleteListener {
                Log.e("TaskComplete", "Task isSuccessful = ${it.isSuccessful}")
                if(it.isSuccessful) {
                    Log.e("TaskComplete", "Is Result from cache = ${it.result!!.metadata.isFromCache}")
                }else {
                    Log.e("TaskComplete", "Task Failed")
                }
            }
    }
}
