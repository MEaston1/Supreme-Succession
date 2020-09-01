package com.northkorea.supremesuccession

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContendersActivity : AppCompatActivity(), ContenderListFragment.OnContenderSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenders)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, ContenderListFragment.newInstance(), "dogList")
                .commit()
        }
    }
    override fun onContenderSelected(contenderModel: ContenderModel) {
        val detailsFragment =
            ContenderDetailsFragment.newInstance(contenderModel)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "contenderDetails")
            .addToBackStack(null)
            .commit()
    }
}