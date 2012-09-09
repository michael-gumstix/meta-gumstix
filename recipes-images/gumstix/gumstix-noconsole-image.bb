DESCRIPTION = "The most basic Gumstix image"

inherit image
IMAGE_FEATURES += "package-management"
IMAGE_EXTRA_INSTALL ?= ""

AUDIO_INSTALL = " \
  alsa-utils-aplay \
  alsa-utils-alsactl \
  alsa-utils-alsamixer \
  alsa-utils-amixer \
  alsa-utils-speakertest \
 "

BASE_INSTALL = " \
  ${MACHINE_EXTRA_RRECOMMENDS} \
  ${@base_contains("DISTRO_FEATURES", "bluetooth", "bluez4", "", d)} \
  avahi-systemd avahi-utils \
  base-files \
  base-passwd \
  bash \
  coreutils \
  dbus \
  devmem2 \
  memtester \
  netbase \
  ntp-systemd \
  net-tools \
  iputils \
  openssh-ssh openssh-keygen openssh-scp openssh-sshd-systemd \
  rsyslog-systemd \
  sed \
  shadow tinylogin \
  systemd systemd-compat-units \
  u-boot-mkimage \
  udev \
  udisks udisks-systemd \
  upower \
  util-linux \
  which \
 "

FIRMWARE_INSTALL = " \
  linux-firmware-sd8686 \
  linux-firmware-rtl8192cu \
  linux-firmware-rtl8192ce \
  linux-firmware-rtl8192su \
  linux-firmware-wl12xx \
 "
NETWORK_INSTALL = " \
  networkmanager \
  networkmanager-tests \
  rfkill \
  ${@base_contains("DISTRO_FEATURES", "wifi", "iw wpa-supplicant", "", d)} \
 "

IMAGE_INSTALL += " \
  ${BASE_INSTALL} \
  ${AUDIO_INSTALL} \
  ${FIRMWARE_INSTALL} \
  ${NETWORK_INSTALL} \
  ${ROOTFS_PKGMANAGE} \
 "

# this section removes remnants of legacy sysvinit support
# for packages installed above
IMAGE_FILE_BLACKLIST += " \
                        /etc/init.d/NetworkManager \
                        /etc/init.d/avahi-daemon \
                        /etc/init.d/dbus-1 \
                        /etc/init.d/dnsmasq \
                        /etc/init.d/networking \
                        /etc/init.d/ntpd \
                        /etc/init.d/sshd \
                        /etc/init.d/udev \
                        /etc/init.d/udev-cache \
                       "

remove_blacklist_files() {
	for i in ${IMAGE_FILE_BLACKLIST}; do
		rm -rf ${IMAGE_ROOTFS}$i
	done

}

ROOTFS_POSTPROCESS_COMMAND =+ "remove_blacklist_files ; "

