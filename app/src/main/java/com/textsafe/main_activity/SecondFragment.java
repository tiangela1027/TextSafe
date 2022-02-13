package com.textsafe.main_activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.navigation.fragment.NavHostFragment;

import com.textsafe.main_activity.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment
    implements LoaderManager.LoaderCallbacks<Cursor>{

    private FragmentSecondBinding binding;
    // Defines a constant that identifies the loader
    static final int DETAILS_QUERY_ID = 0;

    private static final String[] PROJECTION =
            {
                    ContactsContract.Data._ID,
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.Data.DATA1,
                    ContactsContract.Data.DATA2,
                    ContactsContract.Data.DATA3,
                    ContactsContract.Data.DATA4,
                    ContactsContract.Data.DATA5,
                    ContactsContract.Data.DATA6,
                    ContactsContract.Data.DATA7,
                    ContactsContract.Data.DATA8,
                    ContactsContract.Data.DATA9,
                    ContactsContract.Data.DATA10,
                    ContactsContract.Data.DATA11,
                    ContactsContract.Data.DATA12,
                    ContactsContract.Data.DATA13,
                    ContactsContract.Data.DATA14,
                    ContactsContract.Data.DATA15
            };


    // Defines the selection clause
    private static final String SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?";
    // Defines the array to hold the search criteria
    private String[] selectionArgs = { "" };
    /*
     * Defines a variable to contain the selection value. Once you
     * have the Cursor from the Contacts table, and you've selected
     * the desired row, move the row's LOOKUP_KEY value into this
     * variable.
     */
    private String lookupKey = ContactsContract.Data.LOOKUP_KEY;

    /*
     * Defines a string that specifies a sort order of MIME type
     */
    private static final String SORT_ORDER = ContactsContract.Data.MIMETYPE;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initializes the loader framework
        LoaderManager.getInstance(this).initLoader(DETAILS_QUERY_ID, null, this);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        // Choose the proper action
        switch (loaderId) {
            case DETAILS_QUERY_ID:
                // Assigns the selection parameter
                selectionArgs[0] = lookupKey;
                // Starts the query
                CursorLoader mLoader =
                        new CursorLoader(
                                getActivity(),
                                ContactsContract.Data.CONTENT_URI,
                                PROJECTION,
                                SELECTION,
                                selectionArgs,
                                SORT_ORDER
                        );
                return mLoader;
        }
        return null;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case DETAILS_QUERY_ID:
                /*
                * Process the resulting Cursor here.
                */
        }
//        break;
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case DETAILS_QUERY_ID:
                /*
                 * If you have current references to the Cursor,
                 * remove them here.
                 */
        }
//        break;
    }

}