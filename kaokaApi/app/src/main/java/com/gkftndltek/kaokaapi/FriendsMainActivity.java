package com.gkftndltek.kaokaapi;

import com.kakao.friends.AppFriendContext;
import com.kakao.friends.AppFriendOrder;
import com.kakao.friends.response.AppFriendsResponse;
import com.kakao.kakaotalk.callback.TalkResponseCallback;
import com.kakao.kakaotalk.v2.KakaoTalkService;
import com.kakao.network.ErrorResult;

public class FriendsMainActivity {
    public void requestFriends() {
        // offset = 0, limit = 100
        AppFriendContext friendContext = new AppFriendContext(AppFriendOrder.NICKNAME, 0, 100, "asc");

        KakaoTalkService.getInstance().requestAppFriends(friendContext,
                new TalkResponseCallback<AppFriendsResponse>() {
                    @Override
                    public void onNotKakaoTalkUser() {
                        System.out.print("유저가 아님");
                        // 발신자가 카카오톡 유저가 아님
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        System.out.print("에러");
                        // 그 외 에러
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        System.out.print("재로그인");
                        // 액세스토큰 및 리프레시토큰이 만료됨. 재로그인 필요.
                    }

                    @Override
                    public void onSuccess(AppFriendsResponse result) {
                        System.out.print("성공");
                        // context의 beforeUrl과 afterUrl이 업데이트 된 상태.
                    }
                });
    }
}

