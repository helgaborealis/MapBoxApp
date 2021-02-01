package net.borlis.mapboxandroidapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.mapbox.geojson.Feature
import kotlinx.android.synthetic.main.details_dialog.*
import net.borlis.mapboxandroidapp.domain.ADDRESS
import net.borlis.mapboxandroidapp.domain.IMAGE_URL
import net.borlis.mapboxandroidapp.domain.TITLE


class BuildingDetailsDialog(
    context: Context,

    /** FormRecord Model  */
    private val buildingsFeature: Feature,

    ) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove the title of the dialog
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.details_dialog)
        this.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // set Dialog at the bottom of the screen
        setDialogPosition()

        // set up the dialog properties
        this.setCancelable(true)
        this.setCanceledOnTouchOutside(true)
    }

    /** set Dialog at the bottom of the screen */
    private fun setDialogPosition() {
        val layoutParam = this.window?.attributes
        layoutParam?.gravity = Gravity.BOTTOM
        layoutParam?.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window?.attributes = layoutParam
    }

    override fun show() {
        super.show()
        setContentView(R.layout.details_dialog)
        this.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        title.text = buildingsFeature.properties()?.get(TITLE).toString().removeSurrounding("\"")
        address.text = buildingsFeature.properties()?.get(ADDRESS).toString().removeSurrounding("\"")

        Glide.with(context)
            .load(buildingsFeature.properties()?.get(IMAGE_URL).toString().removeSurrounding("\""))
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_house_24)
            .into(imageBuilding);
    }
}
