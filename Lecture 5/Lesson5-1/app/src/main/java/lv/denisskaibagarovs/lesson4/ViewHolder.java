package lv.denisskaibagarovs.lesson4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.textViewAuthor) TextView textViewAuthor;


    public ViewHolder (View view) { ButterKnife.bind(this, view); }
}
