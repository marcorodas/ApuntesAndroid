package area51pe.fragmentmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alumno on 17/12/15.
 */
public class FragmentList extends Fragment {

    private FragmentListInterface myInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myInterface != null){
                    myInterface.onTouch();
                }
            }
        });
        return view;
    }

    public void setFragmentListInterface(final FragmentListInterface fragmentListInterface){
        myInterface = fragmentListInterface;
    }

    public static interface FragmentListInterface{
        public void onTouch();
    }
}
