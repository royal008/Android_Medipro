package com.android.medipro.ui.fragments.InviteToEarn;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteToEarnFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack;
    Button btnShare;


    public InviteToEarnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_invite_to_earn, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);//to hide menu bar
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        btnShare=(Button)view.findViewById(R.id.btn_share_now);

        onClick();
        return view;

    }

    private void onClick()
    {
        ivBack.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.btn_share_now:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String SharingBody ="\"Hey! Check out Medipro! Amazing app to manage your Health.Download the app here: http://bit.ly/MobiShare and enter in the following code MP1234 to get more benefits on Medipro\"\n";
                String SharingSub ="Sharing Sub";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,SharingSub);
                sharingIntent.putExtra(Intent.EXTRA_TEXT,SharingBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));
                break;

        }
    }
}



