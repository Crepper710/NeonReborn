package io.github.crepper710.neon_reborn.eventsystem.events

import io.github.crepper710.neon_reborn.eventsystem.Event

class EventRender2D(val partialTicks: Float): Event()

class EventRender3D(val partialTicks: Float): Event()

class EventKeyPress(val keyCode: Integer): Event()