# TODO: delete if not used
#[[cmake_minimum_required(VERSION 2.8)
project(hmbtcore)
set(CMAKE_BUILD_TYPE Release)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES Utilities.cpp)

LOCAL_MODULE := hmbtcore

LOCAL_LDLIBS := -llog
LOCAL_LDLIBS += -lz
LOCAL_C_INCLUDES += ../../../../high-mobility-bt-core
LOCAL_C_INCLUDES += ../../../../crypto-c/Crypto

LOCAL_SRC_FILES := $(LOCAL_PATH)/hmbtcore.c
LOCAL_SRC_FILES += $(LOCAL_PATH)/hm_bt_crypto_hal.c
LOCAL_SRC_FILES += $(LOCAL_PATH)/hm_bt_debug_hal.c
LOCAL_SRC_FILES += $(LOCAL_PATH)/hm_connectivity_hal.c
LOCAL_SRC_FILES += $(LOCAL_PATH)/hm_bt_persistence_hal.c
LOCAL_SRC_FILES += $(LOCAL_PATH)/hm_api_callback.c

LOCAL_SRC_FILES += ../../../../crypto-c/Crypto/Crypto.c

LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_bt_core.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_bt_log.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_cert.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_conf_access.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_api.c
]]

add_library(hmbtcore SHARED ${SOURCE_FILES})
