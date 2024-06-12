SUMMARY = "Monitoring Client"
LICENSE = "TTTECH-license"
SECTION = "ACM"
HOMEPAGE = "http://www.tttech.com/"

SRC_URI = "${TSN_ACM_SRC_URI}"
SRCREV = "${TSN_ACM_SRCREV}"

TTTECH_DIR = "ngn.ngn-demo/monitoring-client"
S = "${WORKDIR}/git/${TTTECH_DIR}"

SRC_URI += "file://includes"
PR = "st-1.6.7"

EXTRA_OEMAKE = " DESTDIR=${D} "

#TODO: temporary to bypass kernel module
CFLAGS:append = "-I${TSN_ACM_LAYER}/recipes-acm/acm-demo/files/includes"

do_install () {
	cd ${S}
	oe_runmake install
}

BBCLASSEXTEND = "native"
