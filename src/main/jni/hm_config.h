
#ifndef HM_CONFIG_H_
#define HM_CONFIG_H_

#define LED_A_PIN_NO                      15 // Is ON if central is scanning for beacons
#define LED_B_PIN_NO                      16 // Is ON when data is being received from a slave module

//Turns on debug functionality
#define HM_BT_LOG_LEVEL 3
//#define CAN_HACK

//Startup GARARE FLAGS
#define GARAGE_BLE
//#define GARAGE_CAN

//#define SLAVES
#define CTW

////H-M BT
#define HM_BT_IBEACON //This will turn on beacon advertisement
#define HM_BT_UPDATE_NAME //This will turn on beacon major and minor change after every create token
#define APP_TX_POWER				  			4								/**< The Tx power for radio, can be of values, accepted values are -40, -30, -20, -16, -12, -8, -4, 0, and 4 dBm */
#define HM_BT_DEVICE_NAME                   "HM"	                    /**< Name of device. Will be included in the advertising data. */
#define HM_BT_IBEACON_MEASURED_RSSI             0xb8                            /**< The iBeacon's measured RSSI at 1 meter distance in dBm. */
#define HM_BT_IBEACON_MAJOR_VALUE               0x00, 0x22                      /**< Major value used to identify iBeacons. */
#define HM_BT_IBEACON_MINOR_VALUE               0x00, 0x33                      /**< Minor value used to identify iBeacons. */

#define CARKIT

///DEBUG
#ifdef GARAGE_BLE
#define HM_DEBUG_TX_PIN_NUMBER               21u
#define HM_DEBUG_RX_PIN_NUMBER               5u
#endif
#ifdef GARAGE_CAN
#define HM_DEBUG_TX_PIN_NUMBER               21u
#define HM_DEBUG_RX_PIN_NUMBER               6u
#endif

///WIFI
#define HM_WIFI_TX_PIN_NUMBER               6u
#define HM_WIFI_RX_PIN_NUMBER               5u

////H-M SPI Config
#define SPI_OPERATING_FREQUENCY (0x02000000uL << (uint32_t)Freq_1Mbps) // Slave clock frequency

// SPI0.
#define SPI_PSELSCK0            11u                                     	// SPI clock GPIO pin number
#define SPI_PSELMOSI0           13u         	                           	// SPI Master Out Slave In GPIO pin number
#define SPI_PSELMISO0           14u                                     	// SPI Master In Slave Out GPIO pin number

// SPI1.
#define SPI_PSELSCK1            29u                                     	// SPI clock GPIO pin number
#define SPI_PSELMOSI1           28u                                     	// SPI Master Out Slave In GPIO pin number
#define SPI_PSELMISO1           25u                                     	// SPI Master In Slave Out GPIO pin number

#define SPI_TIMEOUT_COUNTER         0x3000uL                               	// Timeout for SPI transaction in units of loop iterations

#define SPI_TX_BUFFER_SIZE          64u                                		// SPI TX buffer size
#define SPI_RX_BUFFER_SIZE          SPI_TX_BUFFER_SIZE                      	// SPI RX buffer size


#endif
