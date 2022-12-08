package com.pranay.filmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.pranay.filmin.R;
import com.pranay.filmin.databinding.LoadStateItemBinding;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener mRetryCallBack;
    public MoviesLoadStateAdapter(View.OnClickListener mRetryCallBack){
        this.mRetryCallBack= mRetryCallBack;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {

        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(parent,mRetryCallBack);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mProgressBar;
        private TextView mErrorMsg;
        private Button mRetry;

        public LoadStateViewHolder(@NonNull ViewGroup parent,
                                   @NonNull View.OnClickListener retryCallBack) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.load_state_item,parent,false));
            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            mProgressBar = binding.progressBar;
            mErrorMsg = binding.errorMsg;
            mRetry = binding.retryButton;
            mRetry.setOnClickListener(retryCallBack);
        }

        public void bind(LoadState loadState){
            if(loadState instanceof LoadState.Error){
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            mProgressBar.setVisibility(
                    loadState instanceof LoadState.Loading ?View.VISIBLE : View.GONE);

            mRetry.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE : View.GONE);

            mErrorMsg.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE : View.GONE);
        }
    }
}
