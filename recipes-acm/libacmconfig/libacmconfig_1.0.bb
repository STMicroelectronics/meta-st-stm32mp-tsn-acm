SUMMARY = "ACM Configuration Interface Library"
SECTION = "ACM"
HOMEPAGE = "http://www.tttech.com/"
LICENSE = "TTTECH-license"

SRC_URI = "${TSN_ACM_SRC_URI}"
SRCREV = "${TSN_ACM_SRCREV}"

TTTECH_DIR = "ngn.acm-config"
S = "${WORKDIR}/git/${TTTECH_DIR}"

SRC_URI += "\
    file://config_acm \
    \
    file://includes \
    "
PV = "st-1.6.7"

#TODO: temporary to bypass kernel module
CFLAGS:append = "-I${TSN_ACM_LAYER}/recipes-acm/libacmconfig/files/includes"

EXTRA_OEMAKE += "EXTERNAL_LIBRARY_VERSION=1.6.7"

do_compile () {
    export CFLAGS="${CFLAGS}"
    oe_runmake all DESTDIR=${D}
}
do_install () {
    oe_runmake install DESTDIR=${D}

    install -d "${D}${sysconfdir}/default"
    install -D -m 0644 "${TSN_ACM_LAYER}/recipes-acm/libacmconfig/files/config_acm" "${D}${sysconfdir}/default/config_acm"
}
