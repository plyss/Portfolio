import axios from "axios"

export const getAllCars = async (setCars) => {
    const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars')
    setCars(res.data)
}

export const getNewestCars = async (setNewestCars) => {
    const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=year&_order=desc')
    setNewestCars(res.data)
}

export const getLatestArrivals = async (setLatestArrivals) => {
    const res = await axios.get('https://my-json-server.typicode.com/plyss/jdmmockjson/cars?_sort=id&_order=desc')
    setLatestArrivals(res.data)
}

export const getCarById = async (carId) => {
    const res = await axios.get(`https://my-json-server.typicode.com/plyss/jdmmockjson/cars/${carId}`)
    setCarDetail(res.data)
}