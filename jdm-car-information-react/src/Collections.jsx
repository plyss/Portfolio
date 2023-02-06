import { useEffect, useContext } from "react"
import { Link } from "react-router-dom"
import axios from "axios"
import './App.css'
import { CarContext } from "./CarProvider"
import { SearchContext } from "./SearchProvider"

export default function Collection() {
    const { cars, setCars } = useContext(CarContext)
    const { search, setSearch } = useContext(SearchContext)

    const handleSearch = (e) => {
        setSearch(e.target.value)
    }

    const getAllCars = async () => {
        const res = await axios.get('http://localhost:3000/cars')
        setCars(res.data)
    }

    useEffect(() => {
        getAllCars()
    }, [])

    const renderImageCard = (car) => (
        <div key={car.id} className="image-card">
            <Link to={`/collections/${car.id}`}>
                <img src={car.image} alt={`${car.make} ${car.model}`} className="image" />
                <p className="collections-title">{car.make} {car.model}</p>
            </Link>
        </div>
    )

    const filteredCars = handleSearch ?
        cars.filter((car) => car.make.toLowerCase().includes(search.toLowerCase()) || car.model.toLowerCase().includes(search.toLowerCase()))
            .map(renderImageCard)
        : cars.map(renderImageCard)

    return (
        <>
            <div>
                <input className="collections-search-input"
                    type="text"
                    placeholder="Cari"
                    value={search}
                    onChange={handleSearch}
                />
            </div>
            <div className="image-container">
                {filteredCars}
            </div>
        </>
    )
}
