import com.tongji.build_logic.depend.dependMMKV
import com.tongji.build_logic.depend.dependMVVM
import com.tongji.build_logic.depend.lib.dependLibBase

plugins {
    id("module-manager")
    id("kotlin-parcelize")
}

dependMMKV()
dependLibBase()
dependMVVM()