package com.gkftndltek.kaokaapi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.kakaotalk.callback.TalkResponseCallback;
import com.kakao.kakaotalk.response.KakaoTalkProfile;
import com.kakao.kakaotalk.v2.KakaoTalkService;
import com.kakao.network.ErrorResult;
import com.kakao.util.helper.log.Logger;


public class NextActivity extends AppCompatActivity {

    private FriendsMainActivity friendsMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        requestProfile(); // 프로필 요청
        friendsMainActivity.requestFriends();
    }
    private abstract class KakaoTalkResponseCallback<T> extends TalkResponseCallback<T> {
        @Override
        public void onNotKakaoTalkUser() {
            Logger.w("not a KakaoTalk user");
            System.out.println("여기에 들어오셨습니까?? 하하하하222222");
        }

        @Override
        public void onFailure(ErrorResult errorResult) {
            Logger.e("failure : " + errorResult);
            System.out.println("여기에 들어오셨습니까?? 하하하하3333333");
        }

        @Override
        public void onSessionClosed(ErrorResult errorResult) {
            //System.out.println("여기에 들어오셨습니까?? 하하하하");
            System.out.println("여기에 들어오셨습니까?? 하하하하11111111111111");
        }

        @Override
        public void onSuccess(T result){
            System.out.println("여기에 들어오셨습니까?? 하하하하");
        }
    }
    public void requestProfile() {
        System.out.println("하이하이!");
        KakaoTalkService.getInstance().requestProfile(new KakaoTalkResponseCallback<KakaoTalkProfile>() {
            @Override
            public void onSuccess(KakaoTalkProfile talkProfile) {
                final String nickName = talkProfile.getNickName();
                final String profileImageURL = talkProfile.getProfileImageUrl();
                final String thumbnailURL = talkProfile.getThumbnailUrl();
                final String countryISO = talkProfile.getCountryISO();

                System.out.println("닉네임 : " + nickName);
                System.out.println(" profileImageURL : " +  profileImageURL);
                System.out.println(" thumbnailURL : " +  thumbnailURL);
                System.out.println(" countryISO  : " +  countryISO );
                System.out.println("리얼로 들어옴?");
            }
        });
    }
}

