cmake_minimum_required(VERSION 4.3.1)
include("../../root.cmake")
project(ReaktorFlexbuffer)

set(flatbuffers_dir ../../.github_modules/flatbuffers)

init()

if(ANDROID)
    add_library(
            ${PROJECT_NAME}
            SHARED
            common/CBase.cpp
            common/CppBase.cpp
            common/Matrix.cpp
            common/Visitor.cpp
            common/NativeFlexBuffer.cpp
            droid/NativeFlexBuffer.cpp
    )

    find_library(log-lib log)

    target_link_libraries(
            ${PROJECT_NAME}
            PUBLIC
            android
            log
    )
elseif(APPLE)
    setup_ios()
    add_library(
            ${PROJECT_NAME}
            STATIC
            common/CBase.cpp
            common/CppBase.cpp
            common/Matrix.cpp
            common/Visitor.cpp
            common/NativeFlexBuffer.cpp
            darwin/Reaktor.mm
    )
    target_compile_options(${PROJECT_NAME} PUBLIC -fobjc-arc)
endif()

if(TARGET ${PROJECT_NAME})
    target_include_directories(
            ${PROJECT_NAME}
            PUBLIC
            ${flatbuffers_dir}/include
            .
    )
endif()

if(NOT TARGET ${PROJECT_NAME})
    add_custom_target(${PROJECT_NAME})
endif()
