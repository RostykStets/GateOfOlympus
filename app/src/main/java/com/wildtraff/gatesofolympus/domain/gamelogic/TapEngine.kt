package com.wildtraff.gatesofolympus.domain.gamelogic

class TapEngine(
    private val onHit: () -> Unit,
    private val onMiss: () -> Unit
) {
    private var currentStage: BrightnessStage = BrightnessStage.DIM
    private var requireDoubleTap: Boolean = false
    private var fakeFlash: Boolean = false
    private var pendingSecondTap = false

    fun updateStage(stage: BrightnessStage) {
        currentStage = stage
    }

    fun updateConditions(score: Int) {
        requireDoubleTap = score >= 20
        fakeFlash = score >= 10
    }

    fun handleTap() {
        if (fakeFlash && currentStage != BrightnessStage.BRIGHT) {
            onMiss()
            return
        }

        if (requireDoubleTap) {
            if (!pendingSecondTap && currentStage == BrightnessStage.BRIGHT) {
                pendingSecondTap = true
                return // Чекаємо другого тапу
            }
            if (pendingSecondTap && currentStage == BrightnessStage.BRIGHT) {
                pendingSecondTap = false
                onHit()
            } else {
                pendingSecondTap = false
                onMiss()
            }
        } else {
            if (currentStage == BrightnessStage.BRIGHT) {
                onHit()
            } else {
                onMiss()
            }
        }
    }
}
