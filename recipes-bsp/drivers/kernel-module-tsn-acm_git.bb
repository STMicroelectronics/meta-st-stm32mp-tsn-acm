SUMMARY = "ACM Kernel Module"
SECTION = "ACM"
HOMEPAGE = "http://www.tttech.com/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

DEPENDS += "kernel-module-edge"

SRC_URI = "${TSN_ACM_SRC_URI}"
SRCREV = "${TSN_ACM_SRCREV}"

TTTECH_DIR = "ngn.ngn-dd"
S = "${WORKDIR}/git/${TTTECH_DIR}"

PV = "st-1.6.7"

SRC_URI += "\
    file://acm_modload.conf \
    file://acm_modprobe.conf \
"

MODULES_MODULE_SYMVERS_LOCATION = "acm"

do_install:append() {
    install -D -m 0644 ${S}/acm/api/acmdrv.h ${D}${includedir}/linux/acm/acmdrv.h

    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 0644 ${TSN_ACM_LAYER}/recipes-bsp/drivers/files/acm_modprobe.conf ${D}${sysconfdir}/modprobe.d/acm_modprobe.conf
    install -d ${D}${sysconfdir}/modules-load.d/
    install -m 0644 ${TSN_ACM_LAYER}/recipes-bsp/drivers/files/acm_modload.conf ${D}${sysconfdir}/modules-load.d/acm_modload.conf
}

FILES:${PN} += "${sysconfdir}/"
FILES_${PN}-dev += "${includedir}/linux/acm/acmdrv.h"

do_compile:prepend() {
    export FLX_MODULE_PATH="${STAGING_INCDIR}/kernel-module-edge/"
}

do_create_runtime_spdx[depends] += "virtual/kernel:do_create_runtime_spdx"
