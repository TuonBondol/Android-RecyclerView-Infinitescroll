package speedev.android.recyclerviewinfinitescroll

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.default_backpress_toolbar_layout.*

/****
 *
 * @author TUON BONDOL Date: 9/1/17.
 *
 */

open class BaseActivity : AppCompatActivity() {

    open fun onSetUpGeneralBackPressToolbar(toolbarTitle: String) {
        setSupportActionBar(tbGeneralBackPress)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_press_white_icon_24dp)

        tvToolbarTitle.text = toolbarTitle
    }

    open fun onSetUpGeneralCrossCloseToolbar(toolbarTitle: String) {
        setSupportActionBar(tbGeneralBackPress)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)

        tvToolbarTitle.text = toolbarTitle
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}