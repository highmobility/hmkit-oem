# This is for building in Linux

# Include openssl crypto
include ../../../crypto-c/Crypto/Makefile
HM_OPENSSL_C  := $(shell cd ../../../crypto-c/Crypto/; pwd)
dir += $(HM_OPENSSL_C)
#Include HM bt core
include ../../../high-mobility-bt-core/Makefile
HM_BT_CORE := $(shell cd ../../../high-mobility-bt-core/; pwd)
dir += $(HM_BT_CORE)

JAVA_HOME:=/usr/lib/jvm/default-java
LOCAL_PATH := $(shell cd ../../../hm-java-core-jni/src/main/jni/; pwd)

# for 32bit prepend -m32
CFLAGS += -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/darwin -D _GNU_SOURCE

#add source paths to vpath
VPATH = $(dir)

# create build dir
_dummy := $(shell mkdir -p ../../../lib)

APP_SOURCES := ./

SRC += $(LOCAL_PATH)/hmbtcore.c
SRC += $(LOCAL_PATH)/hm_api_callback.c
SRC += $(LOCAL_PATH)/hm_bt_crypto_hal.c
SRC += $(LOCAL_PATH)/hm_bt_debug_hal.c
SRC += $(LOCAL_PATH)/hm_connectivity_hal.c
SRC += $(LOCAL_PATH)/hm_bt_persistence_hal.c

LOCAL_SRC_FILES += ../../../../crypto-c/Crypto/Crypto.c

LOCAL_SRC_FILES += $(HM_BT_CORE)/hm_bt_core.c
LOCAL_SRC_FILES += $(HM_BT_CORE)/hm_bt_log.c
LOCAL_SRC_FILES += $(HM_BT_CORE)/hm_cert.c
LOCAL_SRC_FILES += $(HM_BT_CORE)/hm_conf_access.c
LOCAL_SRC_FILES += $(HM_BT_CORE)/hm_api.c

CFLAGS += -Wall -std=c99 -fPIC

CFLAGS += -I$(APP_SOURCES)\
	-I$(HM_BT_CORE)\
	-I$(HM_OPENSSL_C)\
	-I$(LOCAL_PATH)\
	-I$(LOCAL_PATH)/openssl\
	-I/usr/lib/jvm/default-java/include\
	-I/usr/lib/jvm/default-java/include/linux

LFLAGS +=

CC = gcc
TARGET = libhmbtcore.jnilib

all: $(TARGET)

#  Compiling sll src files
$(SRC):
	$(CC) $(CFLAGS) -g $@

# Linking all object files
$(TARGET): $(SRC:.c=.o)
	$(CC) $(CFLAGS) -o ../../../lib/$(TARGET) -shared $^\
	 -L$(LOCAL_PATH)/../prebuiltLibs/linux_x86_64\
	 -L$(LOCAL_PATH)/../prebuiltLibs/linux_x86\
	 -L/usr/lib -lssl -lcrypto

SUBDIR_ROOTS := $(TARGET) $(LOCAL_PATH)
DIRS := . $(shell find $(SUBDIR_ROOTS) -type d)
GARBAGE_PATTERNS := *.o *~ core .depend .*.cmd *.ko *.mod.c
GARBAGE := $(foreach DIR,$(DIRS),$(addprefix $(DIR)/,$(GARBAGE_PATTERNS)))

clean:
	rm -rf $(GARBAGE)