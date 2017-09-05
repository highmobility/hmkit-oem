
#include "hm_bt_debug_hal.h"
#include <stdarg.h>
#include <stdio.h>

void hm_bt_debug_hal_log(const char *str, ...){
  va_list args;
    va_start(args, str);
    char output[3000];
    vsprintf(output, str, args);
    printf(output,"Usage %s, [options] ... ");
    printf("\n");
    va_end(args);
}

void hm_bt_debug_hal_log_hex(const uint8_t *data, const uint16_t length){
  uint16_t i;
    for(i = 0 ; i < length ; i++){
      printf("%02X ", data[i]);
    }
    printf("\n");
}
