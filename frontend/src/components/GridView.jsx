import React, { useEffect, useState, useRef } from 'react';
import './GridView.css';

const GridView = () => {
    const [gridData, setGridData] = useState(null);
    const [isConnected, setIsConnected] = useState(false);
    const [error, setError] = useState(null);
    const [lastUpdate, setLastUpdate] = useState(null);
    const intervalRef = useRef(null);

    const fetchGridData = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/grid-dto');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            console.log('Fetched grid data:', data);
            setGridData(data);
            setIsConnected(true);
            setError(null);
            setLastUpdate(new Date());
        } catch (err) {
            console.error('Error fetching grid data:', err);
            setError(`Failed to fetch grid data: ${err.message}`);
            setIsConnected(false);
        }
    };

    const startPolling = () => {
        // Fetch immediately
        fetchGridData();
        
        // Then set up interval for periodic fetching
        intervalRef.current = setInterval(fetchGridData, 1000); // Poll every 1 second
    };

    const stopPolling = () => {
        if (intervalRef.current) {
            clearInterval(intervalRef.current);
            intervalRef.current = null;
        }
    };

    useEffect(() => {
        startPolling();

        return () => {
            stopPolling();
        };
    }, []);

    const renderGrid = () => {
        if (!gridData) return <div className="loading">Loading grid data...</div>;

        const { width, height, robots, shelves } = gridData;
        const grid = [];

        // Create empty grid
        for (let y = 0; y < height; y++) {
            const row = [];
            for (let x = 0; x < width; x++) {
                row.push({ x, y, type: 'empty' });
            }
            grid.push(row);
        }

        // Place shelves
        shelves.forEach(shelf => {
            if (shelf.x >= 0 && shelf.x < width && shelf.y >= 0 && shelf.y < height) {
                grid[shelf.y][shelf.x] = {
                    ...grid[shelf.y][shelf.x],
                    type: 'shelf',
                    id: shelf.id,
                    occupied: shelf.occupied
                };
            }
        });

        // Place robots
        robots.forEach(robot => {
            if (robot.x >= 0 && robot.x < width && robot.y >= 0 && robot.y < height) {
                grid[robot.y][robot.x] = {
                    ...grid[robot.y][robot.x],
                    type: 'robot',
                    id: robot.id,
                    carrying: robot.carrying
                };
            }
        });

        return (
            <div className="warehouse-grid" style={{ 
                display: 'grid', 
                gridTemplateColumns: `repeat(${width}, 40px)`,
                gap: '2px',
                padding: '20px',
                backgroundColor: '#f0f0f0',
                borderRadius: '8px'
            }}>
                {grid.map((row, y) =>
                    row.map((cell, x) => (
                        <div
                            key={`${x}-${y}`}
                            className={`grid-cell ${cell.type}`}
                            style={{
                                width: '40px',
                                height: '40px',
                                border: '1px solid #ccc',
                                display: 'flex',
                                alignItems: 'center',
                                justifyContent: 'center',
                                fontSize: '12px',
                                fontWeight: 'bold',
                                backgroundColor: getCellColor(cell),
                                color: getCellTextColor(cell)
                            }}
                            title={getCellTooltip(cell)}
                        >
                            {getCellContent(cell)}
                        </div>
                    ))
                )}
            </div>
        );
    };

    const getCellColor = (cell) => {
        switch (cell.type) {
            case 'robot':
                return cell.carrying ? '#ff6b6b' : '#4ecdc4';
            case 'shelf':
                return cell.occupied ? '#ffa726' : '#66bb6a';
            default:
                return '#ffffff';
        }
    };

    const getCellTextColor = (cell) => {
        switch (cell.type) {
            case 'robot':
            case 'shelf':
                return '#ffffff';
            default:
                return '#666666';
        }
    };

    const getCellContent = (cell) => {
        switch (cell.type) {
            case 'robot':
                return cell.carrying ? 'R*' : 'R';
            case 'shelf':
                return cell.occupied ? 'S*' : 'S';
            default:
                return '';
        }
    };

    const getCellTooltip = (cell) => {
        switch (cell.type) {
            case 'robot':
                return `Robot ${cell.id}${cell.carrying ? ' (carrying item)' : ''}`;
            case 'shelf':
                return `Shelf ${cell.id}${cell.occupied ? ' (occupied)' : ' (empty)'}`;
            default:
                return `Empty cell (${cell.x}, ${cell.y})`;
        }
    };

    const requestUpdate = async () => {
        try {
            await fetchGridData();
            console.log('Manual update completed');
        } catch (err) {
            console.error('Failed to update:', err);
            setError('Failed to update');
        }
    };

    const togglePolling = () => {
        if (intervalRef.current) {
            stopPolling();
            setIsConnected(false);
        } else {
            startPolling();
        }
    };

    const formatLastUpdate = () => {
        if (!lastUpdate) return 'Never';
        return lastUpdate.toLocaleTimeString();
    };

    return (
        <div className="grid-view">
            <div className="grid-header">
                <h2>Warehouse Grid View&nbsp;</h2>
                <div className="connection-status">
                    <span className={`status-indicator ${isConnected ? 'connected' : 'disconnected'}`}>
                        {isConnected ? '●' : '○'}
                    </span>
                    <span>{isConnected ? 'Connected (Polling)' : 'Disconnected'}</span>
                </div>
                <div className="controls">
                    <button onClick={requestUpdate} className="update-button">
                        Refresh Now
                    </button>
                    <button onClick={togglePolling} className="toggle-button">
                        {intervalRef.current ? 'Stop Polling' : 'Start Polling'}
                    </button>
                </div>
            </div>

            {error && (
                <div className="error-message">
                    Error: {error}
                </div>
            )}

            <div className="status-info">
                <div className="last-update">
                    Last Update: {formatLastUpdate()}
                </div>
                <div className="polling-status">
                    Polling: {intervalRef.current ? 'Active (1s interval)' : 'Stopped'}
                </div>
            </div>

            <div className="grid-container">
                {renderGrid()}
            </div>

            <div className="legend">
                <h3>Legend:</h3>
                <div className="legend-items">
                    <div className="legend-item">
                        <div className="legend-color" style={{ backgroundColor: '#4ecdc4' }}>R</div>
                        <span>Robot (empty)</span>
                    </div>
                    <div className="legend-item">
                        <div className="legend-color" style={{ backgroundColor: '#ff6b6b' }}>R*</div>
                        <span>Robot (carrying)</span>
                    </div>
                    <div className="legend-item">
                        <div className="legend-color" style={{ backgroundColor: '#66bb6a' }}>S</div>
                        <span>Shelf (empty)</span>
                    </div>
                    <div className="legend-item">
                        <div className="legend-color" style={{ backgroundColor: '#ffa726' }}>S*</div>
                        <span>Shelf (occupied)</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GridView;
