package org.ortynskyi.movier.core.movies

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.lapism.searchview.SearchView
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseActivity

class MovieActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView

    private var movieListFragment: MovieListFragment? = null
    private var menuSearch: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        initToolbar()
        initSearch()
        if (savedInstanceState == null) {
            movieListFragment = MovieListFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, movieListFragment, MovieListFragment::class.java.name)
                    .commit()
        } else {
            movieListFragment = supportFragmentManager.findFragmentByTag(MovieListFragment::class.java.name) as MovieListFragment?
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
//        supportFragmentManager.putFragment(outState, MovieListFragment::class.java.name, movieListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuSearch = menu?.findItem(R.id.search)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {
                menuSearch = item
                if (searchView.isSearchOpen) searchView.close(true)
                else searchView.open(true)
            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initSearch() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnOpenCloseListener(object : SearchView.OnOpenCloseListener {
            override fun onOpen(): Boolean {
                return false
            }

            override fun onClose(): Boolean {
                return false
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                movieListFragment?.onSearchTyped(newText)
                return false
            }
        })
    }
}
