package sch.id.aqilah4.elearning.core.dashboard.transactional;

import android.util.Log;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import sch.id.aqilah4.elearning.BaseApps;
import sch.id.aqilah4.elearning.models.ResponseHistory;
import sch.id.aqilah4.elearning.network.NetworkClient;
import sch.id.aqilah4.elearning.network.RequestAPI;
import sch.id.aqilah4.elearning.utils.SessionManagement;

public class TransactionalPresenter {
    private static final String TAG = TransactionalPresenter.class.getSimpleName();
    TransactionalView view;
    private SessionManagement mSessionManagement;
    private CompositeDisposable mCompositeDisposable;
    private HashMap<String, String> user;

    public TransactionalPresenter(TransactionalView view) {
        this.view = view;
        mSessionManagement  = new SessionManagement(BaseApps.getAppContext());
        loadToken();
    }

    private void loadToken() {
        if (mSessionManagement != null)
            user    = mSessionManagement.getUserDetails();
    }

    public void loadHistory(){
        view.showLoading();
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.myhistory(
                user.get(SessionManagement.key_token).toString().trim(),
                user.get(SessionManagement.key_userkon).toString().trim())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.single())
                .subscribe(
                        history -> handleResponse(history),
                        throwable -> handleError(throwable)
                )
        );
    }

    private void handleResponse(ResponseHistory history){
        view.loadHistory(history);
        view.hideLoading();
    }

    private void handleError(Throwable throwable){
        view.loadHistoryError(throwable.getLocalizedMessage().toString());
        Log.d(TAG, "handleError: "+throwable.toString());
        Log.d(TAG, "handleError: "+user.get(SessionManagement.key_userkon).toString().trim());
        Log.d(TAG, "handleError: "+user.get(SessionManagement.key_token).toString().trim());
        view.hideLoading();
    }

    public void destroyData(){
        if (mCompositeDisposable != null)
            mCompositeDisposable.dispose();
    }
}