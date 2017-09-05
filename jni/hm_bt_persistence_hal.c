
#include "hm_bt_persistence_hal.h"

uint32_t hm_bt_persistence_hal_get_serial(uint8_t *serial){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_local_public_key(uint8_t *public){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_local_private_key(uint8_t *public){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_device_certificate(uint8_t *cert){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_ca_public_key(uint8_t *public){
return 0;
}

uint32_t hm_bt_persistence_hal_get_oem_ca_public_key(uint8_t *public){
return 0;
}

uint32_t hm_bt_persistence_hal_add_access_certificate(uint8_t *serial, uint8_t *public, uint8_t *startDate, uint8_t *endDate, uint8_t commandSize, uint8_t *command){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_access_certificate(uint8_t *serial, uint8_t *public, uint8_t *startDate, uint8_t *endDate, uint8_t *commandSize, uint8_t *command){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_access_certificate_by_index(uint8_t index, uint8_t *serial, uint8_t *public, uint8_t *startDate, uint8_t *endDate, uint8_t *commandSize, uint8_t *command){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_access_certificate_count(uint8_t *count){
  return 0;
}

uint32_t hm_bt_persistence_hal_remove_access_certificate(uint8_t *serial){
  return 0;
}

uint32_t hm_bt_persistence_hal_add_stored_certificate(uint8_t *cert, uint16_t size){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_stored_certificate(uint8_t *serial, uint8_t *cert, uint16_t *size){
  return 0;
}

uint32_t hm_bt_persistence_hal_erase_stored_certificate(uint8_t *serial){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_appid_for_issuer_count(uint8_t *issuer, uint8_t *count){
  return 0;
}

uint32_t hm_bt_persistence_hal_get_appid_for_issuer(uint8_t *issuer, uint8_t index, uint8_t *appid){
  return 0;
}

uint32_t hm_bt_persistence_hel_set_command_count(uint8_t *serial, uint8_t command, uint8_t count){
  return 0;
}

uint32_t hm_bt_persistence_hel_get_command_count(uint8_t *serial, uint8_t command, uint8_t *count){
  return 0;
}

uint32_t hm_bt_persistence_hel_remove_all_command_counts(uint8_t *serial){
  return 0;
}