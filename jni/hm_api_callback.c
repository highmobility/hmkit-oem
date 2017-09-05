
#include "hm_api_callback.h"
#include "hm_cert.h"
#include <string.h>

void hm_api_callback_init(void)
{

}

void hm_api_callback_clock(void)
{

}

void hm_api_callback_ping(void)
{

}

void hm_api_callback_entered_proximity(hm_device_t *device)
{
    //TODO add also app id to device
}

void hm_api_callback_proximity_measured(hm_device_t *device, uint8_t receiver_count, hm_receiver_t *receivers)
{

}

void hm_api_callback_exited_proximity(hm_device_t *device)
{

}

void hm_api_callback_command_incoming(hm_device_t *device, uint8_t *data, uint16_t length)
{

}

void hm_api_callback_command_response(hm_device_t *device, uint8_t *data, uint16_t length){

}


uint32_t hm_api_callback_get_device_certificate_failed(hm_device_t *device, uint8_t *nonce)
{
    return 0;
}

void hm_api_callback_access_certificate_registered(hm_device_t *device, uint8_t *public_key, uint8_t error)
{

}

uint32_t hm_api_callback_pairing_requested(hm_device_t *device){
    return 0;
}

void hm_api_callback_telematics_command_incoming(hm_device_t *device, uint8_t id, uint16_t length, uint8_t *data){

}

uint32_t hm_api_callback_get_current_date_time(uint8_t *day, uint8_t *month, uint8_t *year, uint8_t *minute, uint8_t *hour){
    return 0;
}