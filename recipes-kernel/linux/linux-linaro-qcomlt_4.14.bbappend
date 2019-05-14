FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Add-IMU-ST-driver-patches-for-SPI.patch \
    file://0002-Add-pixel-clock-to-ov5640-driver.patch \
    file://0003-Add-support-for-the-OSD-OSD055A-5.5-MIPI-DSI-AMOLED.patch \
    file://0004-Update-goodix.c-driver-to-be-compatible-with-gt1151.patch \
    file://0005-Add-gumstix-devicetree-for-ov5640-cameras.patch \
    file://0006-Add-gumstix-devicetree-for-osd055a-AMOLED-display.patch \
    file://fragment.cfg \
"

do_configure_append () {
    ${S}/scripts/kconfig/merge_config.sh -m -O ${WORKDIR}/build ${WORKDIR}/build/.config ${WORKDIR}/*.cfg
    yes '' | make oldconfig
}