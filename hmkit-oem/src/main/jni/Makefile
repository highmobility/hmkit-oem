# This is for building in Linux

# Include openssl crypto
HM_OPENSSL_C  := $(shell cd ../../../../hmkit-core-jni/hmkit-crypto-c/; pwd)
dir += $(HM_OPENSSL_C)
#Include HM bt core
HM_BT_CORE := $(shell cd ../../../../hmkit-core-jni/hmkit-core/; pwd)
dir += $(HM_BT_CORE)

JAVA_HOME:=/usr/lib/jvm/default-java
LOCAL_PATH := $(shell cd ../../../../hmkit-core-jni/src/main/jni/; pwd)

# for 32bit prepend -m32
CFLAGS += -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/darwin -D _GNU_SOURCE

#add source paths to vpath
VPATH = $(dir)

# this will create the build dir if does not exist
_create_build_dir := $(shell mkdir -p ../../../../lib)

APP_SOURCES := ./

SRC += $(LOCAL_PATH)/hmbtcore.c
SRC += $(LOCAL_PATH)/hmkit_core_api_callback.c
SRC += $(LOCAL_PATH)/hmkit_core_crypto_hal.c
SRC += $(LOCAL_PATH)/hmkit_core_debug_hal.c
SRC += $(LOCAL_PATH)/hmkit_core_connectivity_hal.c
SRC += $(LOCAL_PATH)/hmkit_core_persistence_hal.c

SRC += ../../../../hmkit-core-jni/hmkit-crypto-c/Crypto.c

SRC += $(HM_BT_CORE)/hmkit_core.c
SRC += $(HM_BT_CORE)/hmkit_core_log.c
SRC += $(HM_BT_CORE)/hmkit_core_cert.c
SRC += $(HM_BT_CORE)/hmkit_core_conf_access.c
SRC += $(HM_BT_CORE)/hmkit_core_api.c

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
TARGET_DIR = ../../../../lib
TARGET = libhmbtcore.jnilib

all: $(TARGET)

#  Compiling sll src files
$(SRC):
	$(CC) $(CFLAGS) -g $@

# Linking all object files
$(TARGET): $(SRC:.c=.o)
	$(CC) $(CFLAGS) -o $(TARGET_DIR)/$(TARGET) -shared $^\
	 -L$(LOCAL_PATH)/../prebuiltLibs/linux_x86_64\
	 -L$(LOCAL_PATH)/../prebuiltLibs/linux_x86\
	 -L/usr/lib -lssl -lcrypto

SUBDIR_ROOTS := $(TARGET) $(LOCAL_PATH) $(TARGET_DIR) $(HM_BT_CORE)
DIRS := . $(shell find $(SUBDIR_ROOTS) -type d)
GARBAGE_PATTERNS := *.o *~ core .depend .*.cmd *.ko *.mod.c *.jnilib
GARBAGE := $(foreach DIR,$(DIRS),$(addprefix $(DIR)/,$(GARBAGE_PATTERNS)))

clean:
	rm -rf $(GARBAGE)