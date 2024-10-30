package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    private val buttonFragment = ButtonFragment()
    private val dieFragment = DieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Only add fragments if this is the first creation
        if (savedInstanceState == null) {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    // In portrait, initially show only the button fragment
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container1, buttonFragment)
                        .commit()
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    // In landscape, show both fragments side by side
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container1, buttonFragment)
                        .add(R.id.container2, dieFragment)
                        .commit()
                }
            }
        }
    }

    /* TODO COMPLETE 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    // Remember to place Fragment transactions on BackStack so then can be reversed

    override fun buttonClicked() {
        // Only handle fragment switching in portrait mode
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Replace button fragment with die fragment and add to back stack
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, dieFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}