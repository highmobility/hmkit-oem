
#Include openssl crypto
include ../../../crypto-c/Crypto/Makefile
HM_OPENSSL_C  := $(shell cd ../../../crypto-c/Crypto/; pwd)
dir += $(HM_OPENSSL_C)
#Include HM bt core
include ../../../high-mobility-bt-core/Makefile
HM_BT_CORE  := $(shell cd ../../../high-mobility-bt-core/; pwd)
dir += $(HM_BT_CORE)

JAVA_HOME:=`/usr/libexec/java_home`
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

CFLAGS += -Wall -std=c99

CFLAGS += -I$(APP_SOURCES)\
	-I$(HM_BT_CORE)\
	-I$(HM_OPENSSL_C)\
	-I/usr/local/opt/openssl/include
	-I/usr/include/openssl

LFLAGS +=

CC = gcc
TARGET = libhmbtcore.so

all: $(TARGET)

#  Compiling sll src files
$(SRC):
	$(CC) $(CFLAGS) -g $@

# Linking all object files
$(TARGET): $(SRC:.c=.o)
	$(CC) $(CFLAGS) -o $(TARGET) -shared $^ -L/usr/local/opt/openssl/lib -L/usr/lib/x86_64-linux-gnu -lssl -lcrypto

clean:
	rm -f $(TARGET) *.o *~
