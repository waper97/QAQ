module.exports = {
    // 选项...
    devServer : {
        port: 7777,
        proxy: {
            '/api': {
                target: 'http://localhost:8888/',
                ws: true,
                changeOrigin: true,
                pathRewrite : {
                    '^/api':'/',
                }
            }
        }
    }
}