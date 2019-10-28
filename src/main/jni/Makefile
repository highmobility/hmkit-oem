//TODO: delete?
#Include openssl crypto
SRC += ../../../Crypto-c/Crypto/Crypto.c

#Include HM bt core
SRC += ../../../high-mobility-bt-core/hm_bt_core.c
SRC += ../../../high-mobility-bt-core/hm_cert.c
SRC += ../../../high-mobility-bt-core/hm_conf_access.c
SRC += ../../../high-mobility-bt-core/hm_api.c
SRC += ../../../high-mobility-bt-core/hm_bt_log.c

OPENSSL_DIR  := $(shell cd ../../../openssl/; pwd)

JAVA_HOME:=/usr/lib/jvm/default-java
CFLAGS += -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/darwin

#add source paths to vpath
VPATH = $(dir)

SRC += hmbtcore.c
SRC += hm_api_callback.c
SRC += hm_bt_crypto_hal.c
SRC += hm_bt_debug_hal.c
SRC += hm_connectivity_hal.c
SRC += hm_bt_persistence_hal.c

CFLAGS += -Wall -fPIC -std=c99

CFLAGS += -I./\
	-I../../../openssl/include\
	-I/usr/lib/jvm/default-java/include\
	-I/usr/lib/jvm/default-java/include/linux\
	-I../../../high-mobility-bt-core\
	-I../../../Crypto-c/Crypto\

#LFLAGS +=

CC = gcc
TARGET = libhmbtcore.jnilib

all: $(TARGET)

#  Compiling sll src files
$(SRC):
	$(CC) $(CFLAGS) -g $@

# Linking all object files
$(TARGET): $(SRC:.c=.o)
	$(CC) $(CFLAGS) -o ../../../lib/$(TARGET) -shared $^ -L$(OPENSSL_DIR) -lssl -lcrypto

clean:
	rm -f ../../../lib/$(TARGET)
	rm -f  *.o
	rm -f ../../../Crypto-c/Crypto/*.o
	rm -f ../../../high-mobility-bt-core/*.o
