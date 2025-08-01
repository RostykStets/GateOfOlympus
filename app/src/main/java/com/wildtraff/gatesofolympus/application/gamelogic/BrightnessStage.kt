package com.wildtraff.gatesofolympus.application.gamelogic

import com.wildtraff.gatesofolympus.R

enum class BrightnessStage(val drawableRes: Int) {
    NOT_SHINING(R.drawable.ball_blue_not_shining),
    BIT_SHINING(R.drawable.ball_blue_bit_shining),
    BIT_MORE(R.drawable.ball_blue_bit_more_shining),
    DIM(R.drawable.ball_blue_mid_shining),
    MID(R.drawable.ball_blue_mid_plus_shining),
    ALMOST(R.drawable.ball_blue_almost_shining),
    BRIGHT(R.drawable.ball_blue_shining);
}