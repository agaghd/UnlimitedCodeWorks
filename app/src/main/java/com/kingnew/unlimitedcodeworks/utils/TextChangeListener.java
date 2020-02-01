package com.kingnew.unlimitedcodeworks.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * author : wjy
 * time   : 2018/05/09
 * desc   : TextWatcher的抽象实现类，实际使用中可以选择性实现需要的方法
 */

public abstract class TextChangeListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
