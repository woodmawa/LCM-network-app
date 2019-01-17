package com.softwood.controller

import com.softwood.domain.Device
import com.softwood.utils.JsonEncodingStyle
import com.softwood.utils.JsonUtils

import java.time.LocalDate
import java.time.LocalDateTime

class TestController {
    JsonUtils jsonGenerator

    static responseFormats = ['json', 'xml']

    TestController () {
        JsonUtils.Options options = new JsonUtils.Options()
        options.setExpandLevels (2)
        options.registerTypeEncodingConverter(LocalDateTime) {it.toString()}
        options.registerTypeEncodingConverter(LocalDate) {it.toString()}
        options.excludeNulls(true)
        options.summaryClassFormEnabled(false)
        options.setJsonEncodingStyle(JsonEncodingStyle.tmf)

        jsonGenerator = options.build()
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Collection<Device> results = Device.list(sort:"name")
        respond results, deviceCount: Device.count()

    }

    def show (Device device) {
        if(device == null) {
            render status:404
        } else {
            respond device}
    }

    def show2 (Device device) {

        if(device == null) {
            render status:404
        } else {
            def json = jsonGenerator.toTmfJson(device).encodePrettily()
            println json

            render json
        }
    }
}
