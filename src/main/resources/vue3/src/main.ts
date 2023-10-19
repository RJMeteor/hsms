import Vue ,{ createApp } from 'vue'
import "animate.css"
// @ts-ignore
import App from "./App.vue"
import {router} from "./config/router.config";
import "element-plus/dist/index.css"
import 'nprogress/nprogress.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const app = createApp(App);
app.use(router)
app.config.warnHandler = () => null
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')