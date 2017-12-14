
#Include openssl crypto
include ../../../crypto-c/Crypto/Makefile
HM_OPENSSL_C  := $(shell cd ../../../crypto-c/Crypto/; pwd)
dir += $(HM_OPENSSL_C)
#Include HM bt core
include ../../../high-mobility-bt-core/Makefile
HM_BT_CORE  := $(shell cd ../../../high-mobility-bt-core/; pwd)
dir += $(HM_BT_CORE)

JAVA_HOME:=/usr/lib/jvm/default-java
CFLAGS += -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/darwin

#add source paths to vpath
VPATH = $(dir)

APP_SOURCES := ./

SRC += hmbtcore.c
SRC += hm_api_callback.c
SRC += hm_bt_crypto_hal.c
SRC += hm_bt_debug_hal.c
SRC += hm_connectivity_hal.c
SRC += hm_bt_persistence_hal.c

LOCAL_SRC_FILES += ../../../../crypto-c/Crypto/Crypto.c

LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_bt_core.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_bt_log.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_cert.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_conf_access.c
LOCAL_SRC_FILES += ../../../../high-mobility-bt-core/hm_api.c

CFLAGS += -Wall -std=c99 -fPIC

CFLAGS += -I$(APP_SOURCES)\
	-I$(HM_BT_CORE)\
	-I$(HM_OPENSSL_C)\
	-I/home/n/Downloads/openssl-1.0.2m/include\
	-I/usr/lib/jvm/default-java/include\
	-I/usr/lib/jvm/default-java/include/linux
#	-I/usr/include/openssl

LFLAGS +=

CC = gcc
TARGET = libhmbtcore.jnilib

all: $(TARGET)

#  Compiling sll src files
$(SRC):
	$(CC) $(CFLAGS) -g $@

# Linking all object files
$(TARGET): $(SRC:.c=.o)
	$(CC) $(CFLAGS) -o ../../../lib/$(TARGET) -shared $^ -L/home/n/Downloads/openssl-1.0.2m -L/usr/local/opt/openssl/lib -L/usr/lib -lssl -lcrypto

clean:
	rm -f $(TARGET) *.o *~
